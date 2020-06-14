public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int body_n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int index=0;
        int planets_n = in.readInt();
        Body[] planets = new Body[planets_n];
        double radius = in.readDouble();
        while (index < planets_n) {
            double xx = in.readDouble();
            double yy = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Body b = new Body(xx, yy, xv, yv, mass, img);
            planets[index] = b;
            index ++;
            }
        return planets;
    }
}
