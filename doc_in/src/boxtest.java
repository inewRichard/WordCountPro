
public class boxtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] onefile = {"javaforcednote"};
		String[] cwlonefile = {"-c","javaforcednote"};
		String[] files = {"*"};
		String[] suffixfiles = {"*.c"};
		String[] afile = {"-a","javaforcednote"};
		String[] ofile = {"javaforcednote","-o","output.txt"};
		String[] cwlfiles = {"-c","-w","-l","*"};
		String[] stoplistfile = {"javaforcednote","-e","stoplist"};
		String[] allparafiles = {"-c","-w","javaforcednote","-o","output.txt","-e","stoplist","-c","*.c","-o","output.tt"};
		String[] out_of_order = {"-c","-a","javaforcednote","-e","stoplist","-o","output.tt","*.c"};
		testmain(out_of_order);

	}

	private static void testmain(String[] onefile) {
		// TODO Auto-generated method stub
		try {
			newlei.main(onefile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
