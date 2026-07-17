public class r7_3 {

    static int stackPos = 3;
    static Integer stack[] = {4, 3, null, null};

    public static boolean push(int inputData){
        if(stackPos <= stack.length){
            stack[stackPos] = inputData;
            stackPos = stackPos + 1;
            return true;
        } else {
            return false;
        }
    }

    public static Integer pop(){
        Integer popData =null;
        if(stackPos > 1){
            stackPos = stackPos - 1;
            popData = stack[stackPos];
            stack[stackPos] = null;
        }
        return popData;
    }

    public static void printStack() {
        System.out.print("stackPos：" + stackPos);
        System.out.print(" stack：");
        for(int i = 0; i < stack.length; i++){
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        // 初期状態
        System.out.println("初期状態");
        printStack();

        // push
        System.out.println("push");
        push(5);
        printStack();

        // pop
        System.out.println("pop");
        Integer data = pop();
        System.out.println("取り出した値：" + data);
        printStack();
    }
}
