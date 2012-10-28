package somePackage;

import java.util.Collection;

import com.crawl.control.Crawler;
import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 * 
 */
public class StartMain {

	/**
	 * Analyzing main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Crawler aCrawl=new Crawler();
		Collection<CrawlResultPackage> aResultColl=aCrawl.crawlWebPages();
		
		for (CrawlResultPackage myPackage:aResultColl){
			System.out.println("myPackage Price="+myPackage.getPriceOfItem());
		}
	}
}