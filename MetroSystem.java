import java.io.*;
import java.util.*;

class Graph<T> {
    private Map<T, List<Pair<T, Float>>> adjacencyList = new HashMap<>();
    private List<T> path = new ArrayList<>();

    public void addEdge(T u, T v, float dist, boolean bidirectional) {
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.putIfAbsent(v, new ArrayList<>());

        adjacencyList.get(u).add(new Pair<>(v, dist));
        if (bidirectional) {
            adjacencyList.get(v).add(new Pair<>(u, dist));
        }
    }

    public void dijkstraSSSP(T source, Map<T, Float> dist, Map<T, T> prev) {
        PriorityQueue<Pair<Float, T>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getFirst));
        dist.clear();
        prev.clear();

        for (T node : adjacencyList.keySet()) {
            dist.put(node, Float.MAX_VALUE);
            prev.put(node, null);
        }

        dist.put(source, 0f);
        pq.add(new Pair<>(0f, source));

        while (!pq.isEmpty()) {
            Pair<Float, T> current = pq.poll();
            float currentDist = current.getFirst();
            T currentNode = current.getSecond();

            if (currentDist > dist.get(currentNode))
                continue;

            for (Pair<T, Float> neighbor : adjacencyList.get(currentNode)) {
                T neighborNode = neighbor.getFirst();
                float weight = neighbor.getSecond();
                float newDist = currentDist + weight;

                if (newDist < dist.get(neighborNode)) {
                    dist.put(neighborNode, newDist);
                    prev.put(neighborNode, currentNode);
                    pq.add(new Pair<>(newDist, neighborNode));
                }
            }
        }
    }

    public void getShortestPathTo(T destination, Map<T, T> prev) {
        path.clear();
        for (T at = destination; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("\t\t\tPath:");
        for (T station : path) {
            System.out.println("\t\t\t" + station);
        }
    }

    public void calcPrice(String sourceStation, String destStation) {
        int maxStations = 235; 
        int[][] fareMatrix = new int[maxStations][maxStations];
        Map<String, Integer> stationMap = new HashMap<>();

        try (BufferedReader fareReader = new BufferedReader(new FileReader("fare.csv"))) {
            String line;
            int i = 0;
            while ((line = fareReader.readLine()) != null) {
                String[] fares = line.split(",");
                for (int j = 0; j < fares.length; j++) {
                    fareMatrix[i][j] = Integer.parseInt(fares[j].trim());
                }
                i++;
            }
        } catch (IOException e) {
            System.err.println("Error reading fare.csv: " + e.getMessage());
            return;
        }

        try (BufferedReader stationReader = new BufferedReader(new FileReader("stations.csv"))) {
            String line;
            stationReader.readLine(); 
            while ((line = stationReader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String stationName = data[1].trim();
                stationMap.put(stationName, id);
            }
        } catch (IOException e) {
            System.err.println("Error reading stations.csv: " + e.getMessage());
            return;
        }

        Integer srcId = stationMap.get(sourceStation);
        Integer destId = stationMap.get(destStation);

        if (srcId == null || destId == null) {
            System.out.println("\n\t\t\tInvalid Station Entered!");
            return;
        }

        int fare = fareMatrix[srcId - 1][destId - 1];
        System.out.println("\n\t\t\t--> Fare is: ₹" + fare);
    }

    public void makeDotFile(String inputFile, String outputFile) {
        String clr = "red"; 
        String delimiter = ",";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write("graph G {\n");
            writer.write("node [shape=rect,dpi=600] margin=0.75\n\n");
            writer.write("//" + clr + "\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(delimiter);
                if (parts.length < 3)
                    continue;

                String a = parts[0].trim();
                String b = parts[1].trim();
                String label = parts[2].trim();

                if (a.equals("Seelampur")) {
                    clr = "blue";
                } else if (a.equals("Golf Course")) {
                    clr = "green";
                } else if (a.equals("Sant Guru Ram Singh Marg")) {
                    clr = "violet";
                } else if (a.equals("JL Nehru Stadium")) {
                    clr = "yellow";
                }

                writer.write("// Current color: " + clr + "\n");

                if (check(a, b)) {
                    writer.write("\"" + a + "\" -- \"" + b + "\" [label=\"" + label + "\",color=" + clr
                            + ",penwidth=\"8\"];\n");
                } else {
                    writer.write("\"" + a + "\" -- \"" + b + "\" [label=\"" + label + "\",color=" + clr
                            + ",penwidth=\"2\"];\n");
                }
            }

            writer.write("}");
            System.out.println("DOT file created successfully: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error creating DOT file: " + e.getMessage());
        }
    }

    private boolean check(String src, String dest) {
        int f = 0;

        for (T node : path) {
            if (node.equals(src)) {
                f++;
            } else if (node.equals(dest)) {
                f++;
            }
        }

        return f == 2;
    }
}

class Pair<K, V> {
    private final K first;
    private final V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}

public class MetroSystem {
    public static void main(String[] args) {

        Graph<String> metro = new Graph<>();

        metro.addEdge("Rithala", "Netaji Subhash Place", 5.2f, true);
        metro.addEdge("Netaji Subhash Place", "Keshav Puram", 1.2f, true);
        metro.addEdge("Keshav Puram", "Kanhaiya Nagar", 0.8f, true);
        metro.addEdge("Kanhaiya Nagar", "Inderlok", 1.2f, true);
        metro.addEdge("Inderlok", "Shastri Nagar", 1.2f, true);
        metro.addEdge("Shastri Nagar", "Pratap Nagar", 1.7f, true);
        metro.addEdge("Pratap Nagar", "Pulbangash", 0.8f, true);
        metro.addEdge("Pulbangash", "Tis Hazari", 0.9f, true);
        metro.addEdge("Tis Hazari", "Kashmere Gate", 1.1f, true);
        metro.addEdge("Kashmere Gate", "Shastri Park", 2.2f, true);
        metro.addEdge("Shastri Park", "Seelampur", 1.6f, true);
        metro.addEdge("Seelampur", "Welcome", 1.1f, true);
        metro.addEdge("Rajouri Garden", "Ramesh Nagar", 1f, true);
        metro.addEdge("Ramesh Nagar", "Moti Nagar", 1.2f, true);
        metro.addEdge("Moti Nagar", "Kirti Nagar", 1f, true);
        metro.addEdge("Kirti Nagar", "Shadipur", 0.7f, true);
        metro.addEdge("Shadipur", "Patel Nagar", 1.3f, true);
        metro.addEdge("Patel Nagar", "Rajender Place", 0.9f, true);
        metro.addEdge("Rajender Place", "Karol Bagh", 1f, true);
        metro.addEdge("Karol Bagh", "Rajiv Chowk", 3.4f, true);
        metro.addEdge("Rajiv Chowk", "Barakhamba Road", 0.7f, true);
        metro.addEdge("Barakhamba Road", "Mandi House", 1f, true);
        metro.addEdge("Mandi House", "Pragati Maiden", 0.8f, true);
        metro.addEdge("Pragati Maiden", "Inderprastha", 0.8f, true);
        metro.addEdge("Inderprastha", "Yamuna Bank", 1.8f, true);
        metro.addEdge("Yamuna Bank", "Laxmi Nagar", 1.3f, true);
        metro.addEdge("Laxmi Nagar", "Nirman Vihar", 1.1f, true);
        metro.addEdge("Nirman Vihar", "Preet Vihar", 1.0f, true);
        metro.addEdge("Preet Vihar", "Karkar Duma", 1.2f, true);
        metro.addEdge("Karkar Duma", "Anand Vihar", 1.1f, true);
        metro.addEdge("Anand Vihar", "Kaushambi", 0.8f, true);
        metro.addEdge("Kaushambi", "Vaishali", 1.6f, true);
        metro.addEdge("Yamuna Bank", "Akshardham", 1.3f, true);
        metro.addEdge("Akshardham", "Mayur Vihar Phase-1", 1.8f, true);
        metro.addEdge("Mayur Vihar Phase-1", "Mayur Vihar Extention", 1.2f, true);
        metro.addEdge("Mayur Vihar Extention", "New Ashok Nagar", 0.9f, true);
        metro.addEdge("New Ashok Nagar", "Noida Sector-15", 1.0f, true);
        metro.addEdge("Noida Sector-15", "Noida Sector-16", 1.1f, true);
        metro.addEdge("Noida Sector-16", "Noida Sector-18", 1.1f, true);
        metro.addEdge("Noida Sector-18", "Botanical Garden", 1.1f, true);
        metro.addEdge("Botanical Garden", "Golf Course", 1.2f, true);
        metro.addEdge("Golf Course", "Noida City Center", 1.3f, true);

        // Green Line
        metro.addEdge("Madipur", "Shivaji Park", 1.1f, true);
        metro.addEdge("Shivaji Park", "Punjabi Bagh", 1.6f, true);
        metro.addEdge("Punjabi Bagh", "Ashok Park", 0.9f, true);
        metro.addEdge("Ashok Park", "Inderlok", 1.4f, true);
        metro.addEdge("Ashok Park", "Sant Guru Ram Singh Marg", 1.1f, true);
        metro.addEdge("Sant Guru Ram Singh Marg", "Kirti Nagar", 1f, true);
        metro.addEdge("Kashmere Gate", "Lal Qila", 1.5f, true);
        metro.addEdge("Lal Qila", "Jama Masjid", 0.8f, true);
        metro.addEdge("Jama Masjid", "Delhi Gate", 1.4f, true);
        metro.addEdge("Delhi Gate", "ITO", 1.3f, true);
        metro.addEdge("ITO", "Mandi House", 0.8f, true);
        metro.addEdge("Mandi House", "Janptah", 1.4f, true);
        metro.addEdge("Janptah", "Central Secretariat", 1.3f, true);
        metro.addEdge("Central Secretariat", "Khan Market", 2.1f, true);
        metro.addEdge("Khan Market", "JL Nehru Stadium", 1.4f, true);
        metro.addEdge("JL Nehru Stadium", "Jangpura", 0.9f, true);
        // Yellow Line
        metro.addEdge("Vishwavidyalaya", "Vidhan Sabha", 1f, true);
        metro.addEdge("Vidhan Sabha", "Civil Lines", 1.3f, true);
        metro.addEdge("Civil Lines", "Kashmere Gate", 1.1f, true);
        metro.addEdge("Kashmere Gate", "Chandni Chowk", 1.1f, true);
        metro.addEdge("Chandni Chowk", "Chawri Bazar", 1f, true);
        metro.addEdge("Chawri Bazar", "New Delhi", 0.8f, true);
        metro.addEdge("New Delhi", "Rajiv Chowk", 1.1f, true);
        metro.addEdge("Rajiv Chowk", "Patel Chowk", 1.3f, true);
        metro.addEdge("Patel Chowk", "Central Secretariat", 0.9f, true);
        metro.addEdge("Central Secretariat", "Udyog Bhawan", 0.3f, true);
        metro.addEdge("Udyog Bhawan", "Lok Kalyan Marg", 1.6f, true);
        metro.addEdge("Lok Kalyan Marg", "Jor Bagh", 1.2f, true);
        metro.addEdge("Samaypur Badli", "Rohini Sector - 18", 0.8f, true);
        metro.addEdge("Rohini Sector - 18", "Haiderpur Badli Mor", 1.3f, true);
        metro.addEdge("Haiderpur Badli Mor", "Jahangirpuri", 1.3f, true);
        metro.addEdge("Jahangirpuri", "Adarsh Nagar", 1.3f, true);
        metro.addEdge("Adarsh Nagar", "Azadpur", 1.5f, true);
        metro.addEdge("Azadpur", "Model Town", 1.4f, true);
        metro.addEdge("Model Town", "GTB Nagar", 1.4f, true);
        metro.addEdge("GTB Nagar", "Vishwavidyalaya", 0.8f, true);
        metro.addEdge("Jor Bagh", "INA", 1.3f, true);
        metro.addEdge("INA", "AIIMS", 0.8f, true);
        metro.addEdge("AIIMS", "Green Park", 1.0f, true);
        metro.addEdge("Green Park", "Hauz Khas", 1.8f, true);
        metro.addEdge("Hauz Khas", "Malviya Nagar", 1.7f, true);
        metro.addEdge("Malviya Nagar", "Saket", 0.9f, true);
        metro.addEdge("Saket", "Qutab Minar", 1.7f, true);
        metro.addEdge("Qutab Minar", "Chhattarpur", 1.3f, true);
        metro.addEdge("Chhattarpur", "Sultanpur", 1.6f, true);
        metro.addEdge("Sultanpur", "Ghitorni", 1.3f, true);
        metro.addEdge("Ghitorni", "Arjan Garh", 2.7f, true);
        metro.addEdge("Arjan Garh", "Guru Dronacharya", 2.3f, true);
        metro.addEdge("Guru Dronacharya", "Sikandarpur", 1.0f, true);
        metro.addEdge("Sikandarpur", "MG Road", 0.9f, true);
        metro.addEdge("MG Road", "Iffco Chowk", 1.5f, true);
        metro.addEdge("Iffco Chowk", "Sushant Lok", 1.5f, true);

        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n\n");
        System.out.print("\t\tEnter source station in capital case: ");
        String sourceStation = sc.nextLine();
        System.out.print("\t\tEnter destination station in capital case: ");
        String destStation = sc.nextLine();

        Map<String, Float> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();

        metro.dijkstraSSSP(sourceStation, dist, prev);

        if (!dist.containsKey(destStation) || dist.get(destStation) == Float.MAX_VALUE) {
            System.out.println("\n\t\t\tInvalid Station Entered!");
            return;
        }

        System.out.println(
                "\n\t\tDistance from " + sourceStation + " to " + destStation + " - " + dist.get(destStation) + " Kms");
        metro.getShortestPathTo(destStation, prev);
        metro.calcPrice(sourceStation, destStation);
        metro.makeDotFile("data.txt", "finalmap.dot");
        try {
            String command = "dot -Tpng finalmap.dot -o path.png";

            @SuppressWarnings("deprecation")
            Process process = Runtime.getRuntime().exec(command);

            process.waitFor();

            System.out.println("Graph image generated successfully as path.png");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
