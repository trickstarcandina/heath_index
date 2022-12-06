import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class OutputWheelAngle2 extends MemberShipFunction {
  constructor() {
    super([
      new Trap('NM', -9999, -9999, -30, -20),
      new Trap('NS', -30, -20, -10, 0),
      new Trimf('Z', -10, 0, 10),
      new Trap('PS', 0, 10, 20, 30),
      new Trap('PM', 20, 30, 9999, 9999),
    ]);
  }
}
export default OutputWheelAngle2;
