package somePackage;

import javax.faces.bean.*;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class SomeBean implements Serializable {
  private static final long serialVersionUID = 1L;
	
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
