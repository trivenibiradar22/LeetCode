class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] part1 = parseComplex(num1);
        int[] part2 = parseComplex(num2);
        
        int real1 = part1[0], imag1 = part1[1];
        int real2 = part2[0], imag2 = part2[1];
        
        int realResult = (real1 * real2) - (imag1 * imag2);
        int imagResult = (real1 * imag2) + (real2 * imag1);
        
        return realResult + "+" + imagResult + "i";
    }
    
    private int[] parseComplex(String num) {
        int plusIndex = num.indexOf('+');
        int real = Integer.parseInt(num.substring(0, plusIndex));
        int imag = Integer.parseInt(num.substring(plusIndex + 1, num.length() - 1));
        return new int[] {real, imag};
    }
}