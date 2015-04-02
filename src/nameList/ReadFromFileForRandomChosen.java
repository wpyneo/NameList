package nameList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReadFromFileForRandomChosen {

	static String strLine = null;
	static int i = 0;
	static ArrayList<String[]> caseList = new ArrayList<String[]>();
	static ArrayList caseCount = new ArrayList();

	static ArrayList<ArrayList<String[]>> totalMember = new ArrayList<ArrayList<String[]>>();
	static ArrayList<String[]> casesPerMember = new ArrayList<String[]>();
	static ArrayList<String[]> chosenCase = new ArrayList<String[]>();

	static int memberID = 0;
	static int caseCountPerMember = 0;

	static JFileChooser fd = new JFileChooser();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		readinData();

		// call remove duplicate method
		// printList(removeDuplicate(caseList));

		// call subtotal method
		// printCount(caseCount);

		randomPick(caseList);

		showChosenCases();

		outputToFile(fd);

	}

	// this is to read in the raw data
	public static void readinData() {
		// read source file from generated csv report

		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		System.out.println(fd.getCurrentDirectory());

		// File f = new File("SC-Cases-feb2015.csv");

		try {
			// create file reader object to read file
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);

			String caseOwner = null;

			// this is to hold the name and total case count
			// String[] memberCaseCount = new String[3];

			boolean flag = true;

			while (flag) {

				// read each line of the report
				strLine = br.readLine();

				// break each line into 3 string separated by ","

				String value[] = strLine.split(",");
				// String value[] = {strLine};

				// System.out.println(value.length);

				if (value.length == 0) {
					// this is to skip reading if the readin content is blank
					continue;
				}

				if (value[0].contains("Grand Totals")) {
					// Normally it mean the readline reachs the end.
					totalMember.add(casesPerMember);
					break;

				} else if (value[0].contains("Case Owner")) {
					// this is the case owner
					String name = (value[0].substring(15));

					// below is to check if this is our member
					// if (name.contains("Changchen Li")
					// || name.contains("Alex Xu")
					// || name.contains("PengYuan Wang")
					// || name.contains("Fei Yin")
					// || name.contains("Sherry Chen")
					// || name.contains("Simon Tian")
					// || name.contains("Kathy See")
					// || name.contains("Peng Gao")
					// || name.contains("Yong Zheng")
					// || name.contains("Tao Yang")
					// || name.contains("Kevin Zhou")
					// || name.contains("Mingming Wei")
					// || name.contains("Quan Li")
					// || name.contains("Fiona Liu")
					// || name.contains("Zhiguo Li")) {
					//
					// // this is to control the first entry
					// // if (caseOwner != null) {
					// // totalMember.add(casesPerMember);
					// // casesPerMember = new ArrayList<String[]>();
					// // }
					//
					// caseOwner = name;
					// memberID++;
					//
					// totalMember.add(casesPerMember);
					// casesPerMember = new ArrayList<String[]>();
					//
					// } else {
					// caseOwner = null;
					// }

					caseOwner = name;
					memberID++;

					totalMember.add(casesPerMember);
					casesPerMember = new ArrayList<String[]>();

				} else if (caseOwner == null) {
					// this is to skip the title of the file
					continue;

				} else {

					// put the second and thrid word into a new array, for
					// future use. These two works normally hold name and
					// case number

					String trueValue[] = { caseOwner, value[0], memberID + "" };

					// add name and case number into caseList for future
					// use.
					caseList.add(trueValue);

					// // case Count per member increase
					// caseCountPerMember++;

					// System.out.println(i + " " + strLine);

					System.out.println(i + " " + trueValue[0] + " "
							+ trueValue[1] + " " + trueValue[2]);

					// add each name and case into casesPerMember, which is used
					// to record all cases for this member
					casesPerMember.add(trueValue);
					// this is just used for count lines
					i++;

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			} else if (i == 0) {
				// if this is the first record, then added it.
				String a[] = { valueA[0], valueA[1] };
				String b[] = { valueB[0], valueB[1] };
				newAL.add(a);
				newAL.add(b);
				// count 1 for this user
				count += 2;

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

	// this is to print name with their unique case number
	public static void printList(ArrayList al) {
		// print name with their unique case number
		Object[] b = (Object[]) al.toArray();
		System.out.println(b.length);

		for (int i = 0; i < b.length; i++) {

			String valueA[] = (String[]) b[i];

			System.out.println(valueA[0] + " " + valueA[1]);
		}
	}

	// this is to print name and their total case count
	public static void printCount(ArrayList al) {

		// print name and their total case count
		Object[] b = (Object[]) al.toArray();
		System.out.println(b.length);

		for (int i = 0; i < b.length; i++) {

			String valueA[] = (String[]) b[i];

			System.out.println(valueA[0] + " " + valueA[1]);
		}
	}

	// this is to pick up a random cases from a member's case list
	public static void randomPick(ArrayList al) {

		for (int i = 0; i < totalMember.size(); i++) {

			if ((totalMember.get(i)).size() > 3) {
				// if current member got more than 3 cases, select 3 random
				// cases
				int a = (int) ((Math.random()) * (totalMember.get(i)).size());
				int b = (int) ((Math.random()) * (totalMember.get(i)).size());
				int c = (int) ((Math.random()) * (totalMember.get(i)).size());

				while (a == b) {
					// if the second one equals the first one, then choose
					// second one again
					b = (int) ((Math.random()) * (totalMember.get(i)).size());
				}

				while ((a == c) || (b == c)) {
					// if the third one equals the first or second one, then
					// choose the third one again
					c = (int) ((Math.random()) * (totalMember.get(i)).size());
				}

				// once finish chosen, add chosen cases into chosenCase
				// ArrayList
				chosenCase.add((totalMember.get(i)).get(a));
				chosenCase.add((totalMember.get(i)).get(b));
				chosenCase.add((totalMember.get(i)).get(c));
			} else {
				System.out.println("Not Enough Sample" + i);
			}
		}
	}

	// this is to print out the member name with chosen case in console
	public static void showChosenCases() {
		for (int i = 0; i < chosenCase.size(); i++) {
			String a = (chosenCase.get(i))[0];
			String b = (chosenCase.get(i))[1];
			System.out.println(a + " " + b);
		}
	}

	// this is to output the result into a file
	public static void outputToFile(JFileChooser fd) {

		// this is to select the input file
		JFileChooser dialog = new JFileChooser();
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter(
				"CSV files (*.csv)", "*.csv");
		dialog.setDialogTitle("Save As");
		dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
		dialog.setDialogType(JFileChooser.SAVE_DIALOG);
		dialog.setFileFilter(csvFilter);
		int result = dialog.showSaveDialog(fd);

		// define the directory of the file
		File file;

		// define the file name
		String fileName = "";

		// define the output file
		File of = null;

		try {
			// get the full name of the file
			if (result == JFileChooser.APPROVE_OPTION) {
				file = dialog.getSelectedFile();
				fileName = file.getAbsolutePath() + ".csv";

				of = new File(fileName);

				System.out.println(fileName);
			}

			// write content into buffer reader
			StringBuffer buf = new StringBuffer();

			// write content in buffer into file
			FileWriter pw = new FileWriter(of);
			BufferedWriter output = new BufferedWriter(pw);

			output.write("Member Name,Case Number");
			output.write(System.getProperty("line.separator"));

			for (int i = 0; i < chosenCase.size(); i++) {

				String a = (chosenCase.get(i))[0];
				String b = (chosenCase.get(i))[1];

				output.write(a + "," + b);
				output.write(System.getProperty("line.separator"));

			}

			output.flush();
			output.close();
			System.out.println("output finished");
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
