package ca.ubc.maxcover.util;

/**
 * Utility methods
 */
public class Utility {

  private static final String TAB = "\t";

  public static String seedToStr(int cnt, int id, int mg, int coverage, int lookUps, int savings, double timeInSec) {
    StringBuilder sb = new StringBuilder();
    sb.append(cnt).append(TAB).append(id).append(TAB)
        .append(mg).append(TAB)
        .append(coverage).append(TAB)
        .append(lookUps).append(TAB)
        .append(savings).append(TAB)
        .append(String.format("%.5f", timeInSec));
    String msg = sb.toString();
    return msg;
  }

  /**
   * running time in minutes
   */
  public static double runningTimeMin(long startTime) {
    return (double) (System.currentTimeMillis() - startTime) / (1000.0 * 60.0);
  }
}
