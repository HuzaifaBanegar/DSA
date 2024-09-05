import java.util.Stack;

public class Userqueue {
    static Stack<Integer> stack1 = new Stack<>();
    static Stack<Integer> stack2 = new Stack<>();

    Userqueue() {
    };

    static void push(int X) {
        stack1.push(X); 
    }

    static void move() {
        while(!stack1.isEmpty()){
            stack2.add(stack1.pop());
        }
        
    }

    static int pop() {
        if (stack2.isEmpty()) {
            move();
        }

        return stack2.pop();
    }

    static int peek() {
        if (stack2.isEmpty()) {
            move();
        }

        return stack2.peek();
    }

    static boolean empty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        Userqueue obj = new Userqueue();
        System.out.println("Pushing 5,4 and 3 into Userqueue");
        obj.push(5);
        obj.push(4);
        obj.push(3);
        System.out.println("Popping out element");
        int param2 = obj.pop();
        System.out.println(param2);
        System.out.println("Peeking element");
        int param3 = obj.peek();
        System.out.println(param3);
        System.out.println("Checking is Empty");
        boolean param4 = obj.empty();
        System.out.println(param4);
        System.out.println("Popping out element");
        int param5 = obj.pop();
        System.out.println(param5);
        System.out.println("Checking is Empty");
        boolean param6 = obj.empty();
        System.out.println(param6);
        System.out.println("Popping out element");
        int param7 = obj.pop();
        System.out.println(param7);
        System.out.println("Checking is Empty");
        boolean param8 = obj.empty();
        System.out.println(param8);
    }
}
