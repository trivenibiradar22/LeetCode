class Solution {
    double radius;
    double xCenter;
    double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {
        while (true) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;

            if (x * x + y * y <= 1) {
                return new double[] {
                    xCenter + x * radius,
                    yCenter + y * radius
                };
            }
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */