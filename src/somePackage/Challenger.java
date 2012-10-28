package somePackage;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author mschimpf
 * 
 */
public class Challenger {

	/**
	 * Analyzing main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line;

		try {
			// http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc
			// http://sfbay.craigslist.org/search/sya?sort=pricedsc&hasPic=1&srchType=A
			url = new URL(
					"http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc");

			is = url.openStream(); // throws an IOException
			dis = new DataInputStream(new BufferedInputStream(is));
			int i = 1;

			// Go to the complete html output. Line by line
			while ((line = dis.readLine()) != null) {
				line = line.trim(); // Cutoff unneeded characters
				// if a item is found
				if (line.matches(".*<a href=\"http://sfbay.craigslist.org/.*html.*>") == true) {
					// get the price
					int priceOfItem = Challenger.getPriceFromString(line);
					// Write it to the console
					System.out.println(i + ". Found price=" + priceOfItem
							+ " line" + line);
					i++;
				}
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
	}

	/**
	 * Extracts the price as int value from Craigslists strings like this <a
	 * href="http://sfbay.craigslist.org/sby/sys/3256407578.html">$1895 - Apple
	 * MacBook Pro MC725LL/A 17-Inch</a
	 * 
	 * @param input
	 *            a complete string which must contains a $ sign as starting
	 *            position. The method reads all number signs after the $ and
	 *            transformed it into a int number
	 * @return
	 */
	public static int getPriceFromString(String input) {
		try {
			String stringNumber = "";

			completeLoop: // Break out mark

			// Search for the first '$' character
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '$') {
					// Get everything after the $ character if it is a number
					for (int j = i + 1; j < input.length(); j++) {
						if (input.charAt(j) > 47 && input.charAt(j) < 72) {
							char aChar = input.charAt(j);
							stringNumber = stringNumber + "" + aChar;
						} else {
							// No numbers left, break out
							break /* continue */completeLoop;
						}
					}
				}
			}

			return new Integer(stringNumber);
		} catch (Exception e) {
			System.err.println(e.toString());
			return -1;
		}
	}
}
