package id.mhafizsir;

import java.lang.reflect.Field;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaskUtility {

  public static String maskFields(Object object) {

    StringBuilder result = new StringBuilder();
    Field[] fields = object.getClass().getDeclaredFields();
    Arrays.stream(fields)
        .forEach(
            field -> {
              field.setAccessible(true);
              try {
                if (field.isAnnotationPresent(MaskMobileNumber.class)) {
                  String value = (String) field.get(object);
                  result
                      .append(field.getName())
                      .append("=")
                      .append(maskMobileNumber(value))
                      .append(", ");
                } else if (field.isAnnotationPresent(MaskEmail.class)) {
                  String value = (String) field.get(object);
                  result.append(field.getName()).append("=").append(maskEmail(value)).append(", ");
                } else {
                  result.append(field.getName()).append("=").append(field.get(object)).append(", ");
                }
              } catch (IllegalAccessException e) {
                log.error("Error while masking fields", e);
              }
            });

    // Remove the last comma and space
    if (!result.isEmpty()) {
      result.setLength(result.length() - 2);
    }

    return result.toString();
  }

  private static String maskEmail(String email) {
    if (email == null || email.length() <= 6) {
      return email;
    }
    int atIndex = email.indexOf('@');
    if (atIndex <= 3) {
      return email; // if the part before @ is too short to mask
    }
    return email.substring(0, 3)
            + "****"
            + email.substring(atIndex);
  }

  private static String maskMobileNumber(String mobileNumber) {
    if (mobileNumber == null || mobileNumber.length() <= 6) {
      return mobileNumber;
    }
    return mobileNumber.substring(0, 3)
        + "****"
        + mobileNumber.substring(mobileNumber.length() - 3);
  }
}
