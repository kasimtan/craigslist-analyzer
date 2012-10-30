package somePackage;

import javax.faces.bean.*;
import org.apache.log4j.Logger;

import com.test.CrawlTest;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class SomeBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  static Logger logger = Logger.getLogger(SomeBean.class);
	
  private String someProperty = "";

  public String getSomeProperty() {
    return(someProperty);
  }

  public void setSomeProperty(String someProperty) {
    this.someProperty = someProperty;
  }
  
  public String someActionControllerMethod() {
    return("some-page");
  }
}
