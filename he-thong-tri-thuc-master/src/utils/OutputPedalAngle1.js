import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class OutputPendalAngle1 extends MemberShipFunction {
  constructor() {
    super([
      new Trap('NB', -9999, -9999, -12, -10),
      new Trap('NM', -12, -10, -6, -4),
      new Trap('NS', -6, -4, -2, 0),
      new Trimf('Z', -2, 0, 2),
      new Trap('PS', 0, 3, 5, 8),
      new Trap('PB', 5, 8, 9999, 9999),
    ]);
  }
}
export default OutputPendalAngle1;
