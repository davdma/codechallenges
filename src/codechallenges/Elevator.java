/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codechallenges;
import java.util.*;

/**
 *
 * @author david
 */
public class Elevator {
    
    // Simulates elevator behavior in building
    public static int[] theLift(final int[][] queues, final int capacity) {
        // tracking place of queue via pointers in int array
        // default value of int arrays in java is zero, thus no need to fill
        int[][] pointers = new int[queues.length][2];
        
        // at initialization count the number of queues with people waiting
        // all pointers need to be set properly during initialization otherwise bad stoppage
        int activeQueues = 0;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].length > 0) {
                activeQueues++;
            }
            while (pointers[i][0] < queues[i].length && queues[i][pointers[i][0]] <= i) {
                    pointers[i][0]++;
            }
            while (pointers[i][1] < queues[i].length && queues[i][pointers[i][1]] >= i) {
                    pointers[i][1]++;
            }
        }
        
        // lift runs until no more people in queues (purpose: deliver people to destination)
        // check if queues empty after each visit by comparing pointers == length
        LiftBox lift = new LiftBox(capacity);
        List<Integer> history = new ArrayList<>();
        
        // start at ground floor only if nobody to board
        if (pointers[0][0] == queues[0].length) {
            history.add(lift.curFloor);
        }
        int lastVisited = -1;
        while (activeQueues > 0) {
            // run lift algorithmic behavior
            // cycle in current direction all the way to end
            
            boolean stop = false;
            for (int k = lift.headingUp ? 1 : -1;
                    lift.headingUp ? lift.curFloor < queues.length : lift.curFloor >= 0;
                    lift.curFloor += k) {
                
                // check if upDest/downDest can deboard, deboard
                if (lift.headingUp ? (lift.upDest.isEmpty() ? false : lift.upDest.peek() == lift.curFloor)
                                   : (lift.downDest.isEmpty() ? false : lift.downDest.peek() == lift.curFloor)) {
                    opDeboard(lift);
                    
                    // deboard edge cases for top and ground floor to prevent repeats:
                    if (lift.curFloor == 0 && pointers[0][0] < queues[0].length) {
                        
                    } else if (lift.curFloor == queues.length - 1 
                            && pointers[queues.length - 1][1] < queues[queues.length - 1].length) {
                    } else {
                        stop = true;
                    }
                } 
                // at each floor check if queue in direction empty, then board
                if (lift.headingUp ? pointers[lift.curFloor][0] < queues[lift.curFloor].length
                                   : pointers[lift.curFloor][1] < queues[lift.curFloor].length) {
                    // elevator stops for all calls, even if full and no one gets out!
                    // thus no need to check capacity
                    activeQueues += opBoard(queues, pointers, lift);
                    stop = true;
                }
                // record history if a stop occurred during iteration
                if (stop) {
                    if (lastVisited != lift.curFloor) {
                        history.add(lift.curFloor);
                        lastVisited = lift.curFloor;
                    }
                }
                stop = false;
            }
            // reset to proper position and change direction
            lift.curFloor += lift.headingUp ? -1 : 1;
            lift.headingUp = !lift.headingUp;
        }
        
        // if last stop not ground floor, add zero
        if (history.get(history.size() - 1) != 0) {
                history.add(0);
        }
        return history.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static int opBoard(final int[][] queues, int[][] pointers, LiftBox lift) {
        // only board if queue was not empty upon arrival (in order for activeQueue tracking)
        int floor = lift.curFloor;
        if (lift.headingUp) {
            // bug: once reached maxcapacity, does not move pointer to correct position
            while (lift.curCapacity < lift.maxCapacity) {
                while (pointers[floor][0] < queues[floor].length && queues[floor][pointers[floor][0]] <= floor) {
                    pointers[floor][0]++;
                }
                if (pointers[floor][0] < queues[floor].length) {
                    lift.upDest.add(queues[floor][pointers[floor][0]]);
                    lift.curCapacity++;
                    do {
                        pointers[floor][0]++;
                    } while (pointers[floor][0] < queues[floor].length && queues[floor][pointers[floor][0]] <= floor);
                } else {
                    break;
                }
            }
        } else {
            while (lift.curCapacity < lift.maxCapacity) {
                while (pointers[floor][1] < queues[floor].length && queues[floor][pointers[floor][1]] >= floor) {
                    pointers[floor][1]++;
                }
                if (pointers[floor][1] < queues[floor].length) {
                    lift.downDest.add(queues[floor][pointers[floor][1]]);
                    lift.curCapacity++;
                    do {
                        pointers[floor][1]++;
                    } while (pointers[floor][1] < queues[floor].length && queues[floor][pointers[floor][1]] >= floor);
                } else {
                    break;
                }
            }
        }
        // check if queue finished --> update activeQueues
        if (pointers[floor][0] >= queues[floor].length 
                && pointers[floor][1] >= queues[floor].length) {
            return -1;
        } else {
            return 0;
        }
        
    }
    
    public static void opDeboard(LiftBox lift) {
            // while loop ensures all repeats of destination
            // are taken off the queue on arrival
            if (lift.headingUp) {
                while (lift.upDest.isEmpty() ? false : lift.upDest.peek() == lift.curFloor) {
                    lift.upDest.remove();
                    lift.curCapacity--;
                }
            } else {
                while (lift.downDest.isEmpty() ? false : lift.downDest.peek() == lift.curFloor) {
                    lift.downDest.remove();
                    lift.curCapacity--;
                }
            }
        }
    
    public static class LiftBox {
        // headingUp, floor, updest downdesst
        int curFloor = 0;
        int curCapacity = 0;
        int maxCapacity = 0;
        boolean headingUp = true;
        
        // stores target floors of current occupants
        Queue<Integer> upDest = new PriorityQueue<>();
        Queue<Integer> downDest = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            }
        });
        
        LiftBox(int maxCapacity) {
            this.maxCapacity = maxCapacity;
        }
    }
}
