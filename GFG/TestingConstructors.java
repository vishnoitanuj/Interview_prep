package GFG;

class A{
	 protected int test;
	A(){
		System.out.println("A primary constructor");
	}
	A(int a){
		System.out.println("A class");
	}
}
class B extends A{
	B(){
		super(2);
		System.out.println("B class");
		A ob=new A();
		ob.test=5;
	}
}
public class TestingConstructors {
	int a;
	TestingConstructors ob;
	public TestingConstructors(int a) {
		this.a=a;
	}

	public static void main(String[] args) {
//		TestingConstructors ob=new TestingConstructors();   //Error
		TestingConstructors ob=new TestingConstructors(2);
		B obj=new B();
		System.out.println(ob.ob);
		obj.test=5;
		A obj1=new A();
		obj.test=5;

	}

}
