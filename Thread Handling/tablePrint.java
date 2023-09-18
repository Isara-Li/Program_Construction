package threadTester;
class Table{
	public synchronized void printTable(int n) {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(n*i);
		}
	}
}

class Thread1 extends Thread{
	Table table;
	public Thread1(Table table) {
		this.table=table;
	}
	public void run() {
		System.out.println("Thread 1");
		table.printTable(5);
	}
}

class Thread2 extends Thread{
	Table table;
	public Thread2(Table table) {
		this.table=table;
	}
	public void run() {
		System.out.println("Thread 2");
		table.printTable(100);
	}
}

public class tablePrint{
	public static void main(String[] args) {
		Table tab = new Table();
		Thread1 one = new Thread1(tab);
		Thread2 two = new Thread2(tab);
		
		one.start();
		two.start();
		}
}