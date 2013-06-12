package parade;

public class ChristmasTree {

    private static void tulostaTahtia(int maara) {
        while (maara > 0) {
            System.out.print("*");
            maara--;

        }
        System.out.println();
    }

    private static void tulostaTyhjaa(int maara) {
        while (maara > 0) {
            System.out.print(" ");
            maara--;
        }
    }

    public static void printTree(int korkeus) {
        int i = 1;
        while (i <= korkeus) {
            tulostaTyhjaa(korkeus - i);
            tulostaTahtia(2 * i - 1);
            i++;
        }

        i = korkeus / 10;
        tulostaTyhjaa(korkeus - i);
        tulostaTahtia(2 * i - 1);

        tulostaTyhjaa(korkeus - i);
        tulostaTahtia(2 * i - 1);
    }
}
