import MemberShipFunction from './MemberShipFunction';
import Trap from './Trap';
import Trimf from './Trimf';

class ConneringRadius extends MemberShipFunction {
  constructor() {
    super([
      new Trimf('S', 0, 3, 6),
      new Trimf('M', 3, 6, 9),
      new Trap('B', 6, 9, 9999, 9999),
    ]);
  }
}
export default ConneringRadius;
