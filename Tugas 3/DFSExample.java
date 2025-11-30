import java.util.*;

class DFSGraph {
    private Map<String, List<String>> adj;
    private Map<String, Integer> nodeValues;

    public DFSGraph() {
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
        addEdge("a5", "a8"); // a8 bisa dicapai dari a4 dan a5, menjadikannya DAG (Directed Acyclic Graph)
        addEdge("a6", "a8");
    }

    public void addEdge(String source, String destination) {
        adj.get(source).add(destination);
    }
    
    // Metode DFS utama
    public void dfsSearch(String startNode, int targetValue) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        
        stack.push(startNode);
        
        System.out.println("\n--- Proses Pencarian DFS (Target: " + targetValue + ") ---");
        
        while (!stack.isEmpty()) {
            String currentNode = stack.pop();
            
            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                int currentValue = nodeValues.get(currentNode);

                System.out.println("Kunjungi Node: " + currentNode + " (Nilai: " + currentValue + ")");
                
                // Cek apakah nilai target ditemukan
                if (currentValue == targetValue) {
                    System.out.println("\n Target ditemukan di Node: " + currentNode + "!");
                    return;
                }
                
                // Tambahkan tetangga ke stack (dalam urutan terbalik agar diproses sesuai urutan)
                // DFS akan memprioritaskan node yang terakhir dimasukkan (misalnya a2 sebelum a3)
                // Kita akan membalikkan urutan agar a2 (sebagai anak pertama dari a1) dikunjungi lebih dulu
                List<String> neighbors = adj.get(currentNode);
                Collections.reverse(neighbors); 
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        System.out.println("  -> Dorong " + neighbor + " ke Stack.");
                    }
                }
                Collections.reverse(neighbors); // Kembalikan urutan asli
            }
        }
        
        System.out.println("\n Target " + targetValue + " tidak ditemukan dalam graf.");
    }
}

public class DFSExample {
    public static void main(String[] args) {
        // Inisialisasi Scanner untuk membaca input pengguna
        Scanner scanner = new Scanner(System.in);
        DFSGraph graph = new DFSGraph(); 
        // Meminta input dari pengguna
        System.out.println("Masukkan nilai target (n) untuk dicari (contoh: 50, 80):");
        int targetValue;
        try {
            targetValue = scanner.nextInt();           
            graph.dfsSearch("a1", targetValue);
            
        } catch (InputMismatchException e) {
            System.err.println("Input tidak valid. Harap masukkan angka bulat.");
        } finally {
            scanner.close(); // Tutup scanner setelah selesai
        }
    }
}