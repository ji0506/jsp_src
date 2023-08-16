package test123;

class Parent {
    int x = 100;
 
    Parent() {
        this(500);
    }
 
    Parent(int x) {
        this.x = x;
    }
 
    int getX() {
        return x;
    }
}
 
class Child extends Parent {
    int x = 4000;
    
    Child() {
        this(5000);
    }
 
    Child(int x) {
        this.x = x;
    }
}
 
public class Main {
    public static void main(String[] args) {

    	int i = 3; int k = 1;
    	switch (i) {
    	case 1: k += 1;
    	case 2: k++;
    	case 3: k = 0;
    	case 4: k += 3;
    	case 5: k -= 10;
    	default: k--;
    	}
    	System.out.print(k);

    	
    	
/*    	Child obj = new Child();
        System.out.println(obj.getX());*/
    }
}