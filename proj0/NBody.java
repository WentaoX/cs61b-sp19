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

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] bodies = NBody.readBodies(filename);

        /** Enables double buffering.
         * A animation technique where all drawing takes place on the offscreen canvas.
         * Only when you call show() does your drawing get copied from the
         * offscreen canvas to the onscreen canvas, where it is displayed
         * in the standard drawing window. */
        StdDraw.enableDoubleBuffering();

        /** Sets up the universe so it goes from
         * -100, -100 up to 100, 100 */
        StdDraw.setScale(-radius *10, radius*10);
//        StdDraw.setScale(-100, 100);


        /* Clears the drawing window. */
        StdDraw.clear();

        StdAudio.play("audio/2001.mid");
        /* Shows the drawing to the screen, and waits 2000 milliseconds. */

        for (double tt = 0; tt <= T; tt = tt+dt) {
            Double[] xForces = new Double[bodies.length];
            Double[] yForces = new Double[bodies.length];

            /* x direction forces */
            int index=0;
            for (Body b: bodies) {
                xForces[index] = b.calcNetForceExertedByX(bodies);
                index++;
            }

            /* y direction forces */
            index=0;
            for (Body b: bodies) {
                yForces[index] = b.calcNetForceExertedByY(bodies);
                index++;
            }

            /* update position */
            index=0;
            for (Body b: bodies) {
                b.update(dt, xForces[index], yForces[index]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            /* Draw each star */
            for (Body b: bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(100);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}

