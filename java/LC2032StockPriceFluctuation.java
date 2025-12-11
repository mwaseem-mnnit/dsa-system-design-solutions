import java.util.*;


/*
* ["StockPrice","update","current","maximum", "minimum","update","update","update","update","update","update","update","update","update","update","current","maximum", "minimum"]
* [[],[1,10],[],[],[],[2,5],[1, 5],[2, 6],[3,5],[4, 1],[5,6],[1, 8], [2, 0], [1, 4], [6, 4], [], [], []]
* */
class StockPrice {
    private final HashMap<Integer, Integer> timestampToPrice;
    private final TreeMap<Integer, Integer> priceToCount;
    private final int[] current;

    StockPrice() {
        this.timestampToPrice = new HashMap<>();
        this.priceToCount = new TreeMap<>();
        this.current = new int[2];
    }

    public int current() {
        return this.current[1];
    }

    public int maximum() {
        return this.priceToCount.lastKey();
    }

    public int minimum() {
        return this.priceToCount.firstKey();
    }

    public void update(int timestamp, int price) {
        this.cleanupIfTimestampExists(timestamp);
        this.updateStockPriceForTimestamp(timestamp, price);
    }

    private void updateStockPriceForTimestamp(int timestamp, int price) {
        this.timestampToPrice.put(timestamp, price);
        if(this.current[0] <= timestamp) {
            this.current[0] = timestamp;
            this.current[1] = price;
        }
        Integer priceCount = this.priceToCount.get(price);
        if(priceCount == null || priceCount == 0) {
            priceCount = 0;
        }
        this.priceToCount.put(price, priceCount + 1);
    }

    private void cleanupIfTimestampExists(int timestamp) {
        Integer previousPrice = this.timestampToPrice.get(timestamp);
        if(previousPrice == null) {
            return;
        }
        Integer priceCount = this.priceToCount.get(previousPrice);
        priceCount--;
        if(priceCount == 0) {
            this.priceToCount.remove(previousPrice);
        } else {
            this.priceToCount.put(previousPrice, priceCount);
        }
    }
}

class StockPrice1 {
    private final HashMap<Integer, Integer> currentPriceForTimestamp;
    private final HashMap<Integer, Integer> priceVsCount;
    private final PriorityQueue<Integer> maximumPriceQueue;
    private final PriorityQueue<Integer> minimumPriceQueue;
    private int[] current;

    public StockPrice1() {
        this.currentPriceForTimestamp = new HashMap<>();
        this.priceVsCount = new HashMap<>();
        this.maximumPriceQueue = new PriorityQueue<>(Collections.reverseOrder());
        this.minimumPriceQueue = new PriorityQueue<>();
        this.current = new int[2];
    }

    public int current() {
        return this.current[1];
    }

    public int maximum() {
        return this.maximumPriceQueue.peek();
    }

    public int minimum() {
        return this.minimumPriceQueue.peek();
    }

    public void update(int timestamp, int price) {
        this.cleanupIfTimestampExists(timestamp);
        this.updateStockPriceForTimestamp(timestamp, price);
    }

    private void cleanupIfTimestampExists(int timestamp) {
        if(!this.currentPriceForTimestamp.containsKey(timestamp)) {
            return;
        }
        Integer previousPrice = this.currentPriceForTimestamp.get(timestamp);
        Integer priceCount = this.priceVsCount.get(previousPrice);
        priceCount--;
        if(priceCount == 0) {
            this.maximumPriceQueue.remove(previousPrice);
            this.minimumPriceQueue.remove(previousPrice);
            this.priceVsCount.remove(previousPrice);
        } else {
            this.priceVsCount.put(previousPrice, priceCount);
        }
    }

    private void updateStockPriceForTimestamp(int timestamp, int price) {
        this.currentPriceForTimestamp.put(timestamp, price);
        if(this.current[0] <= timestamp) {
            this.current[0] = timestamp;
            this.current[1] = price;
        }
        Integer priceCount = this.priceVsCount.get(price);
        if(priceCount == null || priceCount == 0) {
            priceCount = 0;
            this.maximumPriceQueue.add(price);
            this.minimumPriceQueue.add(price);
        }
        this.priceVsCount.put(price, priceCount + 1);
    }

    public static void main(String[] args) {
        StockPrice obj = new StockPrice1();
        obj.update(1, 10);
        System.out.println("current" + obj.current());
        System.out.println("maximum" + obj.maximum());
        System.out.println("minimum" + obj.minimum());
        obj.update(1, 10);
        obj.update(2, 5);
        obj.update(1, 5);
        obj.update(2, 6);
        obj.update(3, 5);
        obj.update(4, 1);
        obj.update(5, 6);
        obj.update(1, 8);
        obj.update(2, 0);
        obj.update(1, 4);
        obj.update(6, 4);
        System.out.println("current" + obj.current());
        System.out.println("maximum" + obj.maximum());
        System.out.println("minimum" + obj.minimum());
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */