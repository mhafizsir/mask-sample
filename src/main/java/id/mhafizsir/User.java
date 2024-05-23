package id.mhafizsir;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {

  @MaskEmail private String email;
  @MaskMobileNumber private String mobilePhone;

  @Override
  public String toString() {
    return MaskUtility.maskFields(this);
  }
}
