// Written by BryanOwens012, 8/29/2015

import java.util.Scanner;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out
				.println("This program converts a file full of Twitter IDs to a list of usernames by visiting twitter.com/intent/user?user_id=THE_USER_ID");
		System.out
				.println("Note that the resulting list of usernames will not perfectly correspond to the IDs in the original IDs file.");
		System.out
				.println("This is because user IDs whose corresponding users no longer exist will be skipped over.");
		System.out.println("Enter the name of the input file now:");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		ReadFromFileObj input = new ReadFromFileObj(fileName);
		System.out.println("Accepted file \"" + fileName + "\".");
		System.out.println();

		UserAgent ua = new UserAgent();
		while (!input.isEmpty()) {
			String id = input.readLine();
			String url = "https://twitter.com/intent/user?user_id=" + id;
			try {
				ua.visit(url);
				String out = ua.doc.findFirst("<span>").getText();
				System.out.println(out);
			} catch (JauntException e) {
				// System.out.println("ERROR: User does not exist");
			}
		}
		input.close();
	}
}
