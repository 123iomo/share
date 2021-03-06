package cn.net.sight.share.sso.json;

public class TestSplitList {

	public static void main(String[] args) {
		String string = "f16-0bd9d6092574 4";
		String[] split = string.split(" ");
		for (String string2 : split) {
			System.out.println(string2);
		}
		
		//4c8-eea727043584
		//4
		
		if(string.contains("+")){
			System.out.println("true"); //ok
		}else{
			System.out.println("sdcas");
		}

	}
}
