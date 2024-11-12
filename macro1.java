import java.util.*;
import java.io.*;

public class macro1 {
    public static void main(String[] args) {
        BufferedReader br;
        String input = null;
        String tt = null;
        String arg = null;
        String macroTokens = null;
        String[] mnt = new String[10];
        String[] AR = new String[20];
        int macroIndex[] = new int[10];
        int mcount = 0, arg_count = 0;
        int index = 1;
        int macro_enc = 0;

        try{
            br = new BufferedReader(new FileReader("Input.txt"));
            File f3 = new File("mnt.txt");
            File f4 = new File("mdt.txt");
            File f5 = new File("adt.txt");
            PrintWriter p3 = new PrintWriter(f3);
            PrintWriter p4 = new PrintWriter(f4);
            PrintWriter p5 = new PrintWriter(f5);
            while((input = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(input," ");
                if(!st.hasMoreTokens()) continue;

                tt = st.nextToken();
                if(tt.equals("MACRO")){
                    macro_enc = 1;
                    if(!st.hasMoreTokens()) continue;
                    tt = st.nextToken();
                    mnt[mcount] = tt;
                    macroIndex[mcount] = index;
                    p3.println(mnt[mcount] + "\t" + macroIndex[mcount]);
                    p4.println(mnt[mcount]);
                    p5.println(mnt[mcount]);
                    mcount++;

                    if(!st.hasMoreTokens())continue;
                    tt = st.nextToken();
                    StringTokenizer t = new StringTokenizer(tt,",");
                    while(t.hasMoreTokens()){
                        arg = t.nextToken();
                        if(arg.charAt(0) == '&'){
                            AR[arg_count] = arg;
                            p5.println(AR[arg_count]);
                            arg_count++;
                        }
                    }
                } else {
                    if(macro_enc == 1){
                        if(input.equals("MEND")){
                            macro_enc = 0;
                            p4.println("MEND");
                        } else{
                            StringTokenizer t = new StringTokenizer(input, " ");
                            while(t.hasMoreTokens()){
                                macroTokens = t.nextToken();
                                if(macroTokens.charAt(0) == '&'){
                                    boolean found = false;
                                    for(int i = 0;i < arg_count;i++){
                                        if(macroTokens.equals(AR[i])){
                                            p4.print("AR"+i);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if(!found){
                                        p4.println(macroTokens);
                                    }
                                }else{
                                    p4.print(macroTokens + " ");
                                }
                                if(!t.hasMoreTokens()){
                                    p4.println();
                                }
                            }
                        }
                    }
                }
                index++;
            }
            p3.close();
            p4.close();
            p5.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
