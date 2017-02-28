package ca.ubc.maxcover.util;

import ca.ubc.maxcover.Main;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Utility methods
 */
public class Utility {

  private static final String TAB = "\t";

  public static void logSeed(int cnt, int id, int mg, int coverage, int lookUps, int savings, double time, Config config, BufferedWriter bw) {
    String seedInfo = seedToStr(cnt, id, mg, coverage, lookUps, savings, time);
    String metaInfo = metaDataToStr(config);
    try {
      Main.LOGGER.info(seedInfo);
      bw.write(seedInfo + TAB + metaInfo + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper function
   */
  private static String metaDataToStr(Config config) {
    StringBuilder sb = new StringBuilder();
    sb.append(config.getAlgorithm().toString());
    return sb.toString();
  }

  /**
   * Helper function
   */
  private static String seedToStr(int cnt, int id, int mg, int coverage, int lookUps, int savings, double time) {
    StringBuilder sb = new StringBuilder();
    sb.append(cnt).append(TAB).append(id).append(TAB)
        .append(mg).append(TAB)
        .append(coverage).append(TAB)
        .append(lookUps).append(TAB)
        .append(savings).append(TAB)
        .append(String.format("%.6f", time));
    return sb.toString();
  }
}
