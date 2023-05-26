public class E {
    public static void main(String args[]){
        String str = "010-0000-0000";
        System.out.println(str.matches("^(010)(-)[0-9]{4}(-)[0-9]{4}$"));
    }
}
