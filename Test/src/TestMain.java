

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="123";
		String s2=new String("123");
		String s3="123";
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
	}
}
