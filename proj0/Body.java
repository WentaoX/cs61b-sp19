import java.lang.Math;


public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    double G = 6.67e-11;

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

    public double calcForceExertedBy(Body b) {
        double f = (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
        return f;
    }

    public double calcForceExertedByX(Body star) {
        double force = 0;
        if (!this.equals(star)) {
            double dx = star.xxPos - this.xxPos;
            double dr = this.calcDistance(star);
            double fx = this.calcForceExertedBy(star) * (dx / dr);
            force += fx;
        }
        return force;
    }

    public double calcForceExertedByY(Body star) {
        double force = 0.0;
        if (!this.equals(star)) {
            double dy = star.yyPos - this.yyPos;
            double dr = this.calcDistance(star);
            double fy = this.calcForceExertedBy(star) * (dy / dr);
            force += fy;
        }
        return force;
    }

    public double calcNetForceExertedByX(Body[] stars) {
        double force = 0.0;
        for (Body star : stars) {
            force += this.calcForceExertedByX(star);
        }
        return force;
    }

    public double calcNetForceExertedByY(Body[] stars) {
        double force = 0;
        for (Body star : stars) {
            force += this.calcForceExertedByY(star);
        }
        return force;
    }

    public void update(double dt, double fX, double fY) {
        // net acceleration
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        // new velocity
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        // new position
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
