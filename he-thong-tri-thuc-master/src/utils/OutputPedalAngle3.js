import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class OutputPedalAngle3 extends MemberShipFunction {
    constructor() {
        super([
            new Trap('NB', -9999, -9999, -15, -10),
            new Trap('NS', -12, -8, -4, 0),
            new Trimf('Z', -2, 0, 2),
            new Trap('PS', 0, 4, 6, 10),
            new Trap('PB', 6, 10, 9999, 9999),
        ]);
    }
}
export default OutputPedalAngle3;
