import java.lang.Math;


public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double d_x = this.xxPos - b.xxPos;
        double d_y = this.yyPos - b.yyPos;
        double distance = Math.sqrt(d_x * d_x + d_y * d_y);
        return distance;
    }
}
