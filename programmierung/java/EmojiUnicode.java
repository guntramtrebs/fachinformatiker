import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.SortedMap;

public class EmojiUnicode {

    public static void main(String[] args) {

        // U+1F643 (128579)	🙃	Gesicht auf dem Kopf stehend	UPSIDE-DOWN FACE
        // in IDE eingefügt per copy and paste von https://de.wikipedia.org/wiki/Unicodeblock_Smileys
        // braucht in Unicode mehr als 16 Bit, muss in UTF-16 deshalb in zwei Zeichen dakodiert werden.
        String s = "\uD83D\uDE43";

        // \uD83D alleine macht keinen Sinn, weil die Kodierung unvollständig ist
        String t = "\uD83D"; // ? wird stattdessen angezeigt
        System.out.println("unvollständiges Unicode-Zeichen: " + t);

        // Ein Zeichen, dass aber zwei Character einnimmt, weil es alleine nicht in
        System.out.println("Zeichen: " + s);
        System.out.println("Länge: " + s.length());

        // Umwandlung in ASCII oder Latin-x ersetzt den Emoji durch "?",
        // weil ein Emoji in diesen Zeichensätzen nicht dargestellt werden kann
        System.out.println("Bytes US_ASCII: " +
                bytesToString(s.getBytes(StandardCharsets.US_ASCII)));
        System.out.println("Bytes ISO_8859_1: " +
                bytesToString(s.getBytes(StandardCharsets.ISO_8859_1)));

        // 4 Byte in UTF8-Kodierung: f0 9f 99 83
        System.out.println("Bytes UTF_8: " +
                bytesToString(s.getBytes(StandardCharsets.UTF_8)));

        // 6 Byte in UTF16-Kodierung, da die Byte Order nicht angegeben ist
        // Bei Weiterverarbeitung kann die Byte Order aus der BOM ausgelesen werden
        // BOM sind die ersten zwei Byte (FE FF)
        // Ausgabe: fe ff d8 3d de 43
        // https://de.wikipedia.org/wiki/Byte_Order_Mark
        System.out.println("Bytes UTF_16: " +
                bytesToString(s.getBytes(StandardCharsets.UTF_16)));

        // Wenn explizit LE (little endian) angegeben wird, wird die BOM weggelassen
        // 3d d8 43 de
        System.out.println("Bytes UTF_16LE: " +
                bytesToString(s.getBytes(StandardCharsets.UTF_16LE)));

        // Wenn explizit BE (big endian) angewendet wird, wird die BOM weggelassen
        // Ausgabe: d8 3d de 43
        System.out.println("Bytes UTF_16BE: " +
                bytesToString(s.getBytes(StandardCharsets.UTF_16BE)));


        // UTF-32
        System.out.println("Bytes UTF-32: " +
                bytesToString(s.getBytes(Charset.forName("UTF-32")
                )));

        // UTF-32BE
        System.out.println("Bytes UTF-32BE: " +
                bytesToString(s.getBytes(Charset.forName("UTF-32BE")
                )));

        // UTF-32LE
        System.out.println("Bytes UTF-32LE: " +
                bytesToString(s.getBytes(Charset.forName("UTF-32LE")
                )));

        // x-UTF-32BE-BOM
        System.out.println("Bytes x-UTF-32BE-BOM: " +
                bytesToString(s.getBytes(Charset.forName("x-UTF-32BE-BOM")
                )));

        // x-UTF-32LE-BOM
        System.out.println("Bytes x-UTF-32LE-BOM: " +
                bytesToString(s.getBytes(Charset.forName("x-UTF-32LE-BOM")
                )));

/*
        // IDE will das nicht kompilieren ...
        // UTF-32-nonexistent
        System.out.println("Bytes UTF-32-nonexistent: " +
                bytesToString(s.getBytes(Charset.forName("UTF-32-nonexistent")
                )));

*/

        //  x-UTF-16LE-BOM

        // An der BOM kann die UTF8-Kodierung und die Endianess erkannt werden:
        // https://de.wikipedia.org/wiki/Byte_Order_Mark#Tabellarische_%C3%9Cbersicht


        // auf Tastatureingabe warten
        System.out.println("Bitte Enter drücken");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Verfügbare Charsets
        System.out.println("Verfügbare Charsets:");
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        for (String charsetName : availableCharsets.keySet()) {
            System.out.println(charsetName);
        }
    }


    // wandelt eine Bytefolge in einen String mit ihrer haxadezimalen Darstellung
    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format(" %02x", b));
        }
        return sb.toString();
    }
}
