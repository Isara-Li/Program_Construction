package threadTester;

import java.util.ArrayList;
import java.util.List;

class StudyRoom {
    private int roomNumber;
    private int capacity;
    private boolean availability;

    public StudyRoom(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availability = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

class StudyRoomUnavailableException extends Exception{
    public StudyRoomUnavailableException(String message){
    	super(message);
    };
}

class ThreadOne extends Thread{
	StudyRoomReservationSystem system;
	
	public ThreadOne(StudyRoomReservationSystem system) {
		this.system = system;
	}
	public void run() {
		try {
            system.reserveStudyRoom(9);
            Thread.sleep(500);
            system.releaseStudyRoom(0);
        } catch (InterruptedException | StudyRoomUnavailableException e) {
            System.out.println(e.getMessage());
        }
	}
}        

class ThreadTwo extends Thread{
	StudyRoomReservationSystem system;
	
	public ThreadTwo(StudyRoomReservationSystem system) {
		this.system = system;
	}
	public void run() {
		 try {
             system.reserveStudyRoom(2);
             Thread.sleep(1500);
             system.releaseStudyRoom(2);
         } catch (InterruptedException | StudyRoomUnavailableException e) {
             System.out.println(e.getMessage());
         }
	}
}       

class ThreadThree extends Thread{
	StudyRoomReservationSystem system;
	
	public ThreadThree(StudyRoomReservationSystem system) {
		this.system = system;
	}
	public void run() {
		try {
            system.reserveStudyRoom(0);
        } catch (StudyRoomUnavailableException e){
        	System.out.println(e.getMessage());
        	}
	}
}       


public class StudyRoomReservationSystem {
    private List<StudyRoom> studyRooms;

    public StudyRoomReservationSystem() {
        studyRooms = new ArrayList<>();
    }

    public void addStudyRoom(StudyRoom studyRoom) {
        studyRooms.add(studyRoom);
    }

    public synchronized void reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        for (StudyRoom studyRoom : studyRooms) {
            if (studyRoom.getRoomNumber() == roomNumber) {
                if (studyRoom.isAvailable()) {
                    studyRoom.setAvailability(false);
                    System.out.println("Study room " + roomNumber + " reserved.");
                    return;
                } else {
                    throw new StudyRoomUnavailableException("Study room " + roomNumber + " is already occupied.");
                }
            }
        }
        throw new IllegalArgumentException("Invalid study room number.");
    }

    public synchronized void releaseStudyRoom(int roomNumber) {
        for (StudyRoom studyRoom : studyRooms) {
            if (studyRoom.getRoomNumber() == roomNumber) {
                studyRoom.setAvailability(true);
                System.out.println("Study room " + roomNumber + " released.");
                return;
            }
        }
        throw new IllegalArgumentException("Invalid study room number.");
    }

    public synchronized void displayStudyRoomStatus() {
        System.out.println("Study Room Status:");
        for (StudyRoom studyRoom : studyRooms) {
            System.out.println("Room Number: " + studyRoom.getRoomNumber() + ", Capacity: " + studyRoom.getCapacity() + ", Availability:" +
                    (studyRoom.isAvailable() ? "Available" : "Occupied"));
        }
    }
    
    public static void main(String[] args) {
        StudyRoomReservationSystem system = new StudyRoomReservationSystem();

        // Creating study rooms.
        StudyRoom room1 = new StudyRoom(0, 40);
        StudyRoom room2 = new StudyRoom(1, 90);
        StudyRoom room3 = new StudyRoom(2, 60);
        
        // Adding study rooms to the system.
        system.addStudyRoom(room1);
        system.addStudyRoom(room2);
        system.addStudyRoom(room3);

        system.displayStudyRoomStatus();

        // Simulate concurrent study room reservation and release operations.
        Thread t1 = new ThreadOne(system);
        Thread t2 = new ThreadTwo(system);
        Thread t3 = new ThreadThree(system);
        
        // Making the treads runnable.
        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        system.displayStudyRoomStatus();
    }
}
