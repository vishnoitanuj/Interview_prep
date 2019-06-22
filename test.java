class test{
    public static void test(){
        System.out.print("Good ");
        throw new RuntimeException();
    }
    public static void B(){
         test();
         System.out.print("This ");
    }
    public static void main(String[] args){
        try{
           B();
        }
        catch(Exception e){
           System.out.print("Time ");
        }
        finally{
           System.out.print("at CodingNinjas");
        }
    }
}