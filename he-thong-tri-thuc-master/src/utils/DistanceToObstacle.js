import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class DistanceToObstacle extends MemberShipFunction {
  constructor() {
    super([
      new Trap('Z', -9999, -9999, 5, 12.5),
      new Trimf('S', 5, 12.5, 20),
      new Trap('B', 12.5, 20, 9999, 9999),
    ]);
  }
}
export default DistanceToObstacle;
