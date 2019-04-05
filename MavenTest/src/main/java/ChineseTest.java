import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class ChineseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oriainal = "生命在于运动";
		String result = ZhConverterUtil.convertToTraditional(oriainal);
		System.out.println(result);
	}
}
