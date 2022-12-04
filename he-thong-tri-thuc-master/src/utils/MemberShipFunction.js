class MemberShipFunction {
  constructor(arrTrimfTrap) {
    this.arrTrimfTrap = arrTrimfTrap || [];
  }

  getTrimfTrap(value) {
    return this.arrTrimfTrap.filter(
      (item) => item.getValueY(value) > 0,
    );
  }
}
export default MemberShipFunction;
