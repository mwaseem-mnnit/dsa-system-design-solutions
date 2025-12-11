let regionGrid: boolean[][];
let visitedNode: boolean[][];
function initialise(rows: number, cols: number) {
    regionGrid = new Array(rows);
    visitedNode = new Array(rows);
    for (let i = 0; i < rows; i++) {
        regionGrid[i] = new Array(cols);
        visitedNode[i] = new Array(cols);
        for (let j = 0; j < cols; j++) {
            regionGrid[i][j] = false;
            visitedNode[i][j] = false;
        }
    }
}
function setRegion(grid: string[]) {
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            let r = 0,
                c = 0;
            switch (grid[i].charAt(j)) {
                case '/':
                    r = i * 3;
                    c = j * 3 + 3 - 1;
                    regionGrid[r][c] = true;
                    regionGrid[r + 1][c - 1] = true;
                    regionGrid[r + 2][c - 2] = true;
                    break;
                case '\\':
                    r = i * 3;
                    c = j * 3;
                    regionGrid[r][c] = true;
                    regionGrid[r + 1][c + 1] = true;
                    regionGrid[r + 2][c + 2] = true;
                    break;
                case ' ':
                    break;
            }
        }
    }
}

function markRegion(regionGrid: boolean[][], i: number, j: number) {
    if (
        i < 0 ||
        i >= regionGrid.length ||
        j < 0 ||
        j >= regionGrid[i].length ||
        regionGrid[i][j] ||
        visitedNode[i][j]
    ) {
        return;
    }
    visitedNode[i][j] = true;
    markRegion(regionGrid, i - 1, j);
    markRegion(regionGrid, i + 1, j);
    markRegion(regionGrid, i, j - 1);
    markRegion(regionGrid, i, j + 1);
}

function regionsBySlashes(grid: string[]): number {
    const rows = grid.length * 3;
    const cols = grid[0].length * 3;
    initialise(rows, cols);
    setRegion(grid);
    let result = 0;
    for (let i = 0; i < regionGrid.length; i++) {
        for (let j = 0; j < regionGrid[i].length; j++) {
            if (!regionGrid[i][j] && !visitedNode[i][j]) {
                result++;
                markRegion(regionGrid, i, j);
            }
        }
    }
    return result;
}
console.log(regionsBySlashes(['/\\', '\\/', '\\/']));
