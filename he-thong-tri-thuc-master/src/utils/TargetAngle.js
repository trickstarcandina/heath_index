import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class TargetAngle extends MemberShipFunction {
  constructor() {
    super([
      new Trap('NB', -9999, -9999, -100, -75),
      new Trap('NM', -100, -70, -40, -10),
      new Trimf('Z', -20, 0, 20),
      new Trap('PM', 10, 40, 70, 100),
      new Trap('PB', 75, 100, 9999, 9999),
    ]);
  }
}
export default TargetAngle;
