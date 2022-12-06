class Trap {
    constructor(label, a, b, c, d) {
        this.label = label;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    getValueY(value) {
        if (value > this.a && value <= this.b)
            return (value - this.a) / (this.b - this.a);
        if (value >= this.b && value <= this.c) return 1;
        if (value >= this.c && value <= this.d)
            return (this.d - value) / (this.d - this.c);
        return 0;
    }

    getValueX(value) {
        if (value > 1 || value < 0) return 0;
        if (this.c !== 9999 && this.b !== -9999) {
            return (this.b + this.c) / 2;
        }
        if (this.c === 9999) return this.a + (this.b - this.a) * value;
        if (this.b === -9999) return this.d + (this.c - this.d) * value
        return 0;
    }
}

export default Trap;
