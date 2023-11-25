import java.nio.charset.Charset;

public class Converter
{  
    private static final int[] bitRepNum = {128, 64, 32, 16, 8, 4, 2, 1};
    private static final Charset utf = Charset.forName("UTF-8");
    
    public static void Binary(String characters, boolean decode)
    {
        if (characters.length() == 0)
        {
            ConversionGui.label.setText("INVALID INPUT: Make sure type the values on the input box");
        }
        else if (decode && !characters.matches("^.*[^0-1\\s+].*$"))
        {
            try
            {
                characters = characters.replaceAll("\\s+", "");
                String[] eachBinary = characters.split("(?<=\\G........)");
                String str = "";
            
                for (int i = 0; i < eachBinary.length; i++)
                {
                    String bi = eachBinary[i];
                    int totalBit = 0;
                    for (int bit = 0; bit < bitRepNum.length; bit++){
                        char eachBit = bi.charAt(bit);
                        if (eachBit == '1'){
                            totalBit += bitRepNum[bit];
                        }
                    }
                    str += "" + (char)totalBit;
                }
                ConversionGui.outputArea.append(str);
            }
            catch (IndexOutOfBoundsException e)
            {
                ConversionGui.label.setText("INVALID INPUT: Make sure your input is written in 8 bit pairs! So any number of bits as long as it is a factor of 8 is acceptable (Current: " + characters.length() + " bits)");
            }
        }
        else if (!decode)
        {
            encoding:
            {
                byte[] bytes = characters.getBytes(utf);
                String binary = "";
            
                for (int b = 0; b < bytes.length; b++)
                {
                    int bt = bytes[b];
                    if (bt < 0)
                    {
                        char wrongChar = characters.charAt(b);
                        ConversionGui.label.setText("INVALID INPUT: " + "\'" + wrongChar + "\', please make sure every character in the input is included in the ASCII Table or has decimal number between 0 to 255 inclusive");
                        break encoding;
                    }

                    for (int bit = 0; bit < bitRepNum.length; bit++)
                    {
                        if (bt >= bitRepNum[bit])
                        {
                            bt -= bitRepNum[bit];
                            binary += "1";
                        }
                        else
                        {
                            binary += "0";
                        }
                    }
                    binary += " ";
                }
                ConversionGui.outputArea.append(binary);
            }
        }
        else
        {
            int index = 0;
            characters = characters.replaceAll("\\s+", "");
            while (characters.charAt(index) == '1' || characters.charAt(index) == '0')
            {
                index++;
            }
            ConversionGui.label.setText("INVALID INPUT: \'" + characters.charAt(index) + "\', Make sure every value is 0 or 1");
        }
    }
}



