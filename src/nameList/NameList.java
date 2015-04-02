package nameList;

public class NameList {

	private static String[][] aa = new String[9][2];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		constructeName();
		constructeList();
		
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i][0]+" " + aa[i][1]);
		}
		
		sortOrder();
		
		System.out.println();

		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i][0]+" " + aa[i][1]);
		}
	}

	public static void constructeName() {

		aa[0][0] = "Fei";
		aa[1][0] = "Kevin";
		aa[2][0] = "Sherry";
		aa[3][0] = "Albert";
		aa[4][0] = "Neo";
		aa[5][0] = "Ming";
		aa[6][0] = "Richard";
		aa[7][0] = "Simon";
		aa[8][0] = "Yong";
	}
	
	public static void constructeList(){
		aa[0][1] = "9";
		aa[1][1] = "8";
		aa[2][1] = "7";
		aa[3][1] = "0";
		aa[4][1] = "5";
		aa[5][1] = "4";
		aa[6][1] = "3";
		aa[7][1] = "2";
		aa[8][1] = "1";
	}

	public static void sortOrder() {

		for (int i = 0; i < aa.length; i++) {
			for (int j = 0; j < aa.length; j++) {
				String medium;
				if (Integer.parseInt(aa[i][1]) < Integer.parseInt(aa[j][1])) {

					medium = aa[j][1];
					aa[j][1] = aa[i][1];
					aa[i][1] = medium;
					
					medium = aa[j][0];
					aa[j][0] = aa[i][0];
					aa[i][0] = medium;
					
				}
			}
		}
	}

}
