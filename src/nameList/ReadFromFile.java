package nameList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromFile {

	static String strLine = null;
	static int i = 0;
	static ArrayList caseList = new ArrayList();
	static ArrayList caseCount = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// read source file from generated csv report
		File f = new File("report1411646454001.csv");

		try {
			// create file reader object to read file
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);

			while (true) {

				// read each line of the report
				strLine = br.readLine();

				// break each line into 3 string separated by ","
				String value[] = strLine.split(",");

				if (value.length != 3) {
					// if the line is not usable, which mean the words read in
					// is not equals to 3 then quit the reading. Normally it
					// mean the readline reachs the end.
					break;
				} else if (i == 0) {
					// do not read the first line as it is header
				} else {

					// put the second and thrid word into a new array, for
					// future use. These two works normally hold name and case
					// number
					String trueValue[] = { value[1], value[2] };

					// add name and case number into caseList for future use.
					caseList.add(trueValue);

					System.out.println(strLine + i);

					// System.out.println(value[1] + value[2] + "      " + i);
				}
				// this is just used for count lines
				i++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// call remove duplicate method
		printList(removeDuplicate(caseList));

		// call subtotal method
		printCount(caseCount);

	}

	// this method will remove the duplicated entry for the input arrayList
	public static ArrayList removeDuplicate(ArrayList al) {

		ArrayList newAL = new ArrayList();

		// transfer the input arraylist into array
		Object[] list = (Object[]) al.toArray();

		// count cases for each name
		int count = 0;

		for (int i = 0; i < list.length - 1; i++) {

			// break the current record into 2 object
			String valueA[] = (String[]) list[i];

			// break next record into 2 object
			String valueB[] = (String[]) list[i + 1];

			System.out.println(valueA[0] + " " + valueA[1] + " " + valueB[0]
					+ " " + valueB[1] + " " + valueA[0].equals(valueB[0]) + " "
					+ valueA[1].equals(valueB[1]));

			if (valueA[0].equals(valueB[0]) && valueA[1].equals(valueB[1])
					&& i == 0) {
				// if this is the first record, then added it.
				String a[] = { valueA[0], valueA[1] };
				newAL.add(a);
				// count 1 for this user
				count++;
			} else if (valueA[0].equals(valueB[0])
					&& valueA[1].equals(valueB[1]) == false) {

				// if name is the same but case number is different, add the
				// next one it. The reason for this is because the previous one
				// is already added
				String a[] = { valueB[0], valueB[1] };
				newAL.add(a);
				// count 1 for this user
				count++;
			} else if (valueA[0].equals(valueB[0]) == false
					&& valueA[1].equals(valueB[1]) == false) {
				// if name and case number are different, add the next one
				String a[] = { valueB[0], valueB[1] };
				newAL.add(a);

				// summarise the total case count for previous name and added
				// into caseCount. Reason for that is because we now starting
				// count case for a new name
				String b[] = { valueA[0], count + "" };
				caseCount.add(b);

				// reset case count as it is now for a new name
				count = 0;
				// count 1 for new user
				count++;
			} else if ((valueA[0].equals(valueB[0])
					&& valueA[1].equals(valueB[1]) && i == (list.length - 2))) {

				// if this is the last entry. summarize the total case count for
				// the last name and add it into caseCount
				String b[] = { valueA[0], count + "" };
				caseCount.add(b);
			}

		}
		return newAL;

	}

	public static void printList(ArrayList al) {
		// print name with their unique case number
		Object[] b = (Object[]) al.toArray();
		System.out.println(b.length);

		for (int i = 0; i < b.length; i++) {

			String valueA[] = (String[]) b[i];

			System.out.println(valueA[0] + " " + valueA[1]);
		}
	}

	public static void printCount(ArrayList al) {

		// print name and their total case count
		Object[] b = (Object[]) al.toArray();
		System.out.println(b.length);

		for (int i = 0; i < b.length; i++) {

			String valueA[] = (String[]) b[i];

			System.out.println(valueA[0] + " " + valueA[1]);
		}
	}

}
