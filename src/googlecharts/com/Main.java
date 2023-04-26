package googlecharts.com;

import com.google.gson.Gson;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Cargar los datos del archivo JSON
        Gson gson = new Gson();
        String fileName = "./bin/googlecharts/com/datos.json";
        String json = Files.lines(Paths.get(fileName)).collect(Collectors.joining());
        Datos datos = gson.fromJson(json, Datos.class);

        // Crear el contenido HTML dinámicamente
        String html = "<html>\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        google.charts.load('current', {'packages':['sankey']});\n" +
                "        google.charts.setOnLoadCallback(drawChart);\n" +
                "        function drawChart() {\n" +
                "            var data = new google.visualization.DataTable();\n" +
                "            data.addColumn('string', 'From');\n" +
                "            data.addColumn('string', 'To');\n" +
                "            data.addColumn('number', 'Weight');\n" +
                "            data.addRows([\n";
        for (String[] row : datos.data) {
            html += "                ['" + row[0] + "', '" + row[1] + "', " + row[2] + "],\n";
        }
        html += "            ]);\n" +
                "            var colors = ['#a6cee3', '#b2df8a', '#fb9a99', '#fdbf6f', '#cab2d6', '#ffff99', '#1f78b4', '#33a02c'];\n" +
                "            var options = {\n" +
                "                height: 400,\n" +
                "                sankey: {\n" +
                "                    node: {\n" +
                "                        colors: colors\n" +
                "                    },\n" +
                "                    link: {\n" +
                "                        colorMode: 'gradient',\n" +
                "                        colors: colors\n" +
                "                    }\n" +
                "                }\n" +
                "            };\n" +
                "            var chart = new google.visualization.Sankey(document.getElementById('sankey_basic'));\n" +
                "            chart.draw(data, options);\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"sankey_basic\" style=\"width: 900px; height: 300px;\"></div>\n" +
                "</body>\n" +
                "</html>";

        // Escribir el contenido HTML en un archivo
        String htmlFileName = "./src/googlecharts/com/google.html";
        FileWriter writer = new FileWriter(htmlFileName);
        writer.write(html);
        writer.close();
        
     // Abrir el archivo HTML en el navegador
        String url = new File("src/googlecharts/com/google.html").toURI().toString();
        Desktop.getDesktop().browse(URI.create(url));

    }
}

class Datos {
    String[][] data = {
            {"A", "X", "5"},
            {"A", "Y", "7"},
            {"A", "Z", "6"},
            {"B", "X", "2"},
            {"B", "Y", "9"},
            {"B", "Z", "4"},
            {"C", "X", "6"},
            {"C", "Y", "2"},
            {"C", "Z", "8"}
    };
}



