package GFG;

public class TestingConstructors {
	int a;
	TestingConstructors ob;
	public TestingConstructors(int a) {
		this.a=a;
	}

	public static void main(String[] args) {
		TestingConstructors ob=new TestingConstructors(2);
		System.out.println(ob.ob);

	}

}
