import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';

class DistanceToObstacle2 extends MemberShipFunction {
    constructor() {
        super([
            new Trap('Z', -9999, -9999, 1.5, 5),
            new Trap('S', 0, 5, 25, 30),
            new Trap('B', 25, 30, 9999, 9999),
        ]);
    }
}
export default DistanceToObstacle2;
