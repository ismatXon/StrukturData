
import java.util.*;

class BFSGraph {
    private Map<String, List<String>> adj;
    private Map<String, Integer> nodeValues;

    public BFSGraph() {
        adj = new HashMap<>();
        nodeValues = new HashMap<>();
        // Inisialisasi node dan nilai
        String[] nodes = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"};
        int[] values = {10, 20, 30, 40, 50, 60, 70, 80};
        for (int i = 0; i < nodes.length; i++) {
            adj.put(nodes[i], new LinkedList<>());
            nodeValues.put(nodes[i], values[i]);
        }
        
        // Tentukan hubungan antar node (Edges)
        addEdge("a1", "a2");
        addEdge("a1", "a3");
        addEdge("a2", "a4");
        addEdge("a2", "a5");
        addEdge("a3", "a6");
        addEdge("a3", "a7");
        addEdge("a4", "a8");
        addEdge("a5", "a8");
        addEdge("a6", "a8");
    }

    public void addEdge(String source, String destination) {
        adj.get(source).add(destination);
    }
    
    // Metode BFS utama
    public void bfsSearch(String startNode, int targetValue) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);
        
        System.out.println("\n--- Proses Pencarian BFS (Target: " + targetValue + ") ---");
        
        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            int currentValue = nodeValues.get(currentNode);
           
            System.out.println("Kunjungi Node: " + currentNode + " (Nilai: " + currentValue + ")");
            
            // Cek apakah nilai target ditemukan
            if (currentValue == targetValue) {
                System.out.println("\n Target ditemukan di Node: " + currentNode + "!");
                return;
            }
            
            // Tambahkan semua tetangga yang belum dikunjungi ke antrian
            for (String neighbor : adj.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    System.out.println("  -> Tambahkan " + neighbor + " ke Antrian.");
                }
            }
        }
        
        System.out.println("\n Target " + targetValue + " tidak ditemukan dalam graf.");
    }
}

public class BFSExample {
    public static void main(String[] args) {
        // Inisialisasi Scanner untuk membaca input pengguna
        Scanner scanner = new Scanner(System.in);
        BFSGraph graph = new BFSGraph();
        System.out.println("Masukkan nilai target (n) untuk dicari (contoh: 50, 80):");
        int targetValue;
        try {
            targetValue = scanner.nextInt();
            
            // Lakukan pencarian BFS dengan nilai target dari input
            graph.bfsSearch("a1", targetValue);
            
        } catch (InputMismatchException e) {
            System.err.println("Input tidak valid. Harap masukkan angka bulat.");
        } finally {
            scanner.close(); // Tutup scanner setelah selesai
        }
    }
}