/*
 *   created by mohdwaseem
 *   created on 09/09/25 8:50 pm
 *   To change this template use File | Settings | File and Code Templates.
 */

class NodeTs {
    public next: NodeTs;
    public previous: NodeTs;
    public key: number;
    public value: number;
    constructor(next: NodeTs, previous: NodeTs, key: number, value: number) {
        this.next = next;
        this.previous = previous;
        this.key = key;
        this.value = value;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */

class LRUCache {
    private headNode: NodeTs;
    private tailNode: NodeTs;
    private hashMap: Map<number, NodeTs>;
    private capacity: number;

    constructor(capacity: number) {
        this.capacity = capacity;
        this.hashMap = new Map<number, NodeTs>();
    }

    private getNode(key: number): NodeTs {
        const node = this.hashMap.get(key);
        if (!node) {
            return undefined;
        }
        this.removeFromList(node);
        this.addFront(node);
        return node;
    }

    get(key: number): number {
        const node = this.getNode(key);
        if (!node) {
            return -1;
        }
        return node.value;
    }

    put(key: number, value: number): void {
        let node = this.getNode(key);
        if (node) {
            node.value = value;
            return;
        }
        if (this.capacity > 0 && this.hashMap.size == this.capacity) {
            const node = this.tailNode;
            this.hashMap.delete(node.key);
            this.removeFromList(node);
        }
        node = new NodeTs(null, null, key, value);
        this.addFront(node);
        this.hashMap.set(key, node);
    }

    private addFront(node: NodeTs) {
        if (this.headNode) {
            node.next = this.headNode;
            this.headNode.previous = node;
        }
        this.headNode = node;
        if (!this.tailNode) {
            this.tailNode = node;
        }
    }

    private removeFromList(node: NodeTs) {
        if (this.headNode == node) {
            this.headNode = node?.next;
        }

        if (this.tailNode == node) {
            this.tailNode = node?.previous;
        }

        if (node.previous) {
            node.previous.next = node.next;
        }
        if (node.next) {
            node.next.previous = node.previous;
        }
        node.previous = null;
        node.next = null;
    }
}
