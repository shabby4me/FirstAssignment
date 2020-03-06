 

/**
 * Created by Yang on 1/23/20.
 */
public class StringArrayUtils {
    /**
     * @param array array of String objects
     * @return first element of specified array
     */ // TODO
    public static String getFirstElement(String[] array) {
        if(array.length==0) return null;
        return array[0];
    }

    /**
     * @param array array of String objects
     * @return second element in specified array
     */
    public static String getSecondElement(String[] array) {
        if(array.length<2) return null;
        return array[1];
    }

    /**
     * @param array array of String objects
     * @return last element in specified array
     */ // TODO
    public static String getLastElement(String[] array) {
        if(array.length==0) return null;
        return array[array.length-1];
    }

    /**
     * @param array array of String objects
     * @return second to last element in specified array
     */ // TODO
    public static String getSecondToLastElement(String[] array) {
        if(array.length<2) return null;
        return array[array.length-2];
    }
    
    /** 
     * @param target target string
     * @return array of next table in KMP algorithm
     */
    private static int[] getNextTable(String str){
        if(str.length()==0){
            return null;
        }
        int[] lps = new int[str.length()];
        int[] next = new int[str.length()];
        lps[0] = 0;
        int i = 1; // always walks forward
        int j = 0; // tracks prefix that matches suffix
    
        while (i < str.length()) {
          if (str.charAt(i) == str.charAt(j)) {
            j++;
            lps[i] = j;
            i++;
          } else { // mismatch
            if (j == 0) { // go onto next character in string
              lps[i] = 0;
              i++;
            } else { // backtrack j to check previous matching prefix
              j = lps[j - 1];
            }
          }
        }
         next[0]=-1;
          for(i=1;i<lps.length;++i){
              next[i]=lps[i-1];
              if(str.charAt(i)==str.charAt(next[i])){
                  next[i]=next[next[i]];
              }
          }
        return next;
    }
    
    
    private static int findSubstring(String haystack, String needle) {
          if (haystack == null || needle == null || haystack.length() < needle.length()) {
              return -1;
          } else if (needle.isEmpty()) {
              return 0;
          }
    
          int[] next = getNextTable(needle);
          /*
          for(int i=0;i<lps.length;++i){
              System.out.println(lps[i]);
          }
          */
          int i = 0;
          int j = 0;
    
          while (i < haystack.length()) {
              if (j<0 || needle.charAt(j) == haystack.charAt(i)) {
                  i++;
                  j++;
                  if (j == needle.length()) {
                      return i - j; // match found. Return location of match
                  }
              } else {
                  if (j == 0) {
                      i++;
                  } else {
                      j = next[j]; // backtrack j to check previous matching prefix
                  }
              }
          }
    
          return -1; // did not find needle
    }

    /**
     * @param array array of String objects
     * @param value value to check array for
     * @return true if the array contains the specified `value`
     */ // TODO
    public static boolean contains(String[] array, String value) {

        for(int i=0;i<array.length;++i){
            if(findSubstring(array[i],value)>=0)
                return true;
        }
        return false;
    }

    /**
     * @param array of String objects
     * @return an array with identical contents in reverse order
     */ // TODO
    public static String[] reverse(String[] array) {
        for(int i=0;i<array.length/2;++i){
            String tmp = array[i];
            array[i]=array[array.length-1-i];
            array[array.length-1-i] = tmp;
        }
        return array;
    }

    /**
     * @param array array of String objects
     * @return true if the order of the array is the same backwards and forwards
     */ // TODO
    public static boolean isPalindromic(String[] array) {
        for(int i=0;i<array.length/2;++i){
            if(array[i]!=array[array.length-1-i])
                return false;
        }
        return true;
    }

    /**
     * @param array array of String objects
     * @return true if each letter in the alphabet has been used in the array
     */ // TODO
    public static boolean isPangramic(String[] array) {
        boolean[] used= new boolean[26];
        for(int i=0;i<26;++i){
            used[i]=false;
        }
        for(int i=0;i<array.length;++i){
            for(int j=0;j<array[i].length();++j){
                int pos = array[i].charAt(j)-'a';
                if(!(pos>=0 && pos<=25)){
                    pos = array[i].charAt(j)-'A';
                    if(!(pos>=0 && pos<=25)){
                        continue;
                    }
                }
                if(!used[pos]){
                    used[pos] = true;
                }
            }
        }
        for(int i=0;i<26;++i){
            if(!used[i])
                return false;
        }
        return true;
    }

    /**
     * @param array array of String objects
     * @param value value to check array for
     * @return number of occurrences the specified `value` has occurred
     */ // TODO
    public static int getNumberOfOccurrences(String[] array, String value) {
        int cnt=0;
        for(int i =0;i<array.length;++i){
            if(array[i]==value)
                cnt++;
        }
        return cnt;
    }

    /**
     * @param array         array of String objects
     * @param valueToRemove value to remove from array
     * @return array with identical contents excluding values of `value`
     */ // TODO
    public static String[] removeValue(String[] array, String valueToRemove) {
        int cnt=0;
        for(int i=0;i<array.length;++i){
            if(array[i]==valueToRemove){
                cnt++;
            }
        }
        String[] ret = new String[array.length-cnt];
        cnt=0;
        for(int i=0;i<array.length;++i){
            if(array[i]!=valueToRemove){
                ret[cnt]=array[i];
                cnt++;
            }
        }
        return ret;
    }

    /**
     * @param array array of chars
     * @return array of Strings with consecutive duplicates removes
     */ // TODO
    public static String[] removeConsecutiveDuplicates(String[] array) {
        if(array.length==0)
            return array;
        int cnt=1;
        for(int i=1;i<array.length;i++){
            if(array[i]!=array[i-1]){
                array[cnt]=array[i];
                cnt++;
            }
        }
        String[] ret = new String[cnt];
        for(int i=0;i<cnt;++i){
            ret[i]=array[i];
        }
        return ret;
    }

    /**
     * @param array array of chars
     * @return array of Strings with each consecutive duplicate occurrence concatenated as a single string in an array of Strings
     */ // TODO
    public static String[] packConsecutiveDuplicates(String[] array) {
        if(array.length==0)
            return array;
        int cnt=1;
        for(int i=1;i<array.length;i++){
            if(array[i]!=array[i-1]){
                cnt++;
            }
        }
        String[] ret = new String[cnt];
        cnt=0;
        ret[0]=array[0];
        for(int i=1;i<array.length;i++){
            if(!array[i].equals(array[i-1])){
                cnt++;
                ret[cnt]=new String(array[i]);
                
            }else{
                ret[cnt] = ret[cnt]+array[i];
            }
        }
        return ret;
    }


}
