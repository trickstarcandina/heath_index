import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';

class SpeedCurrent extends MemberShipFunction {
  constructor() {
    super([
      new Trap('Z', -9999, -9999, 0.6, 2.5),
      new Trap('S', 0, 4, 6, 10),
      new Trap('M', 5, 10, 15, 20),
      new Trap('B', 15, 20, 9999, 9999),
    ]);
  }
}
export default SpeedCurrent;
