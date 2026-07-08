class Solution {
    public String convert(String s, int numRows) {

        // No zigzag needed
        if (numRows == 1 || numRows >= s.length())
            return s;

        StringBuilder[] rows = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++)
            rows[i] = new StringBuilder();

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {

            rows[currentRow].append(c);

            // Change direction at first or last row
            if (currentRow == 0 || currentRow == numRows - 1)
                goingDown = !goingDown;

            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder answer = new StringBuilder();

        for (StringBuilder row : rows)
            answer.append(row);

        return answer.toString();
    }
}