import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class OutputWheelAngle1 extends MemberShipFunction {
  constructor() {
    super([
      new Trap('NB', -9999, -9999, -45, -30),
      new Trap('NM', -45, -30, -15, 0),
      new Trimf('Z', -20, 0, 20),
      new Trap('PM', 0, 15, 30, 45),
      new Trap('PB', 30, 45, 9999, 9999),
    ]);
  }
}
export default OutputWheelAngle1;
